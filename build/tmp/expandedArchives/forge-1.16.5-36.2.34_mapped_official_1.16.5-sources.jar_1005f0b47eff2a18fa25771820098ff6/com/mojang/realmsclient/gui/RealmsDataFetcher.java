package com.mojang.realmsclient.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.realmsclient.client.RealmsClient;
import com.mojang.realmsclient.dto.RealmsNews;
import com.mojang.realmsclient.dto.RealmsServer;
import com.mojang.realmsclient.dto.RealmsServerPlayerLists;
import com.mojang.realmsclient.util.RealmsPersistence;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@OnlyIn(Dist.CLIENT)
public class RealmsDataFetcher {
   private static final Logger LOGGER = LogManager.getLogger();
   private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
   private volatile boolean stopped = true;
   private final Runnable serverListUpdateTask = new RealmsDataFetcher.ServerListUpdateTask();
   private final Runnable pendingInviteUpdateTask = new RealmsDataFetcher.PendingInviteUpdateTask();
   private final Runnable trialAvailabilityTask = new RealmsDataFetcher.TrialAvailabilityTask();
   private final Runnable liveStatsTask = new RealmsDataFetcher.LiveStatsTask();
   private final Runnable unreadNewsTask = new RealmsDataFetcher.UnreadNewsTask();
   private final Set<RealmsServer> removedServers = Sets.newHashSet();
   private List<RealmsServer> servers = Lists.newArrayList();
   private RealmsServerPlayerLists livestats;
   private int pendingInvitesCount;
   private boolean trialAvailable;
   private boolean hasUnreadNews;
   private String newsLink;
   private ScheduledFuture<?> serverListScheduledFuture;
   private ScheduledFuture<?> pendingInviteScheduledFuture;
   private ScheduledFuture<?> trialAvailableScheduledFuture;
   private ScheduledFuture<?> liveStatsScheduledFuture;
   private ScheduledFuture<?> unreadNewsScheduledFuture;
   private final Map<RealmsDataFetcher.Task, Boolean> fetchStatus = new ConcurrentHashMap<>(RealmsDataFetcher.Task.values().length);

   public boolean isStopped() {
      return this.stopped;
   }

   public synchronized void init() {
      if (this.stopped) {
         this.stopped = false;
         this.cancelTasks();
         this.scheduleTasks();
      }

   }

   public synchronized void initWithSpecificTaskList() {
      if (this.stopped) {
         this.stopped = false;
         this.cancelTasks();
         this.fetchStatus.put(RealmsDataFetcher.Task.PENDING_INVITE, false);
         this.pendingInviteScheduledFuture = this.scheduler.scheduleAtFixedRate(this.pendingInviteUpdateTask, 0L, 10L, TimeUnit.SECONDS);
         this.fetchStatus.put(RealmsDataFetcher.Task.TRIAL_AVAILABLE, false);
         this.trialAvailableScheduledFuture = this.scheduler.scheduleAtFixedRate(this.trialAvailabilityTask, 0L, 60L, TimeUnit.SECONDS);
         this.fetchStatus.put(RealmsDataFetcher.Task.UNREAD_NEWS, false);
         this.unreadNewsScheduledFuture = this.scheduler.scheduleAtFixedRate(this.unreadNewsTask, 0L, 300L, TimeUnit.SECONDS);
      }

   }

   public boolean isFetchedSinceLastTry(RealmsDataFetcher.Task p_225083_1_) {
      Boolean obool = this.fetchStatus.get(p_225083_1_);
      return obool == null ? false : obool;
   }

   public void markClean() {
      for(RealmsDataFetcher.Task realmsdatafetcher$task : this.fetchStatus.keySet()) {
         this.fetchStatus.put(realmsdatafetcher$task, false);
      }

   }

   public synchronized void forceUpdate() {
      this.stop();
      this.init();
   }

   public synchronized List<RealmsServer> getServers() {
      return Lists.newArrayList(this.servers);
   }

   public synchronized int getPendingInvitesCount() {
      return this.pendingInvitesCount;
   }

   public synchronized boolean isTrialAvailable() {
      return this.trialAvailable;
   }

   public synchronized RealmsServerPlayerLists getLivestats() {
      return this.livestats;
   }

   public synchronized boolean hasUnreadNews() {
      return this.hasUnreadNews;
   }

   public synchronized String newsLink() {
      return this.newsLink;
   }

   public synchronized void stop() {
      this.stopped = true;
      this.cancelTasks();
   }

   private void scheduleTasks() {
      for(RealmsDataFetcher.Task realmsdatafetcher$task : RealmsDataFetcher.Task.values()) {
         this.fetchStatus.put(realmsdatafetcher$task, false);
      }

      this.serverListScheduledFuture = this.scheduler.scheduleAtFixedRate(this.serverListUpdateTask, 0L, 60L, TimeUnit.SECONDS);
      this.pendingInviteScheduledFuture = this.scheduler.scheduleAtFixedRate(this.pendingInviteUpdateTask, 0L, 10L, TimeUnit.SECONDS);
      this.trialAvailableScheduledFuture = this.scheduler.scheduleAtFixedRate(this.trialAvailabilityTask, 0L, 60L, TimeUnit.SECONDS);
      this.liveStatsScheduledFuture = this.scheduler.scheduleAtFixedRate(this.liveStatsTask, 0L, 10L, TimeUnit.SECONDS);
      this.unreadNewsScheduledFuture = this.scheduler.scheduleAtFixedRate(this.unreadNewsTask, 0L, 300L, TimeUnit.SECONDS);
   }

   private void cancelTasks() {
      try {
         if (this.serverListScheduledFuture != null) {
            this.serverListScheduledFuture.cancel(false);
         }

         if (this.pendingInviteScheduledFuture != null) {
            this.pendingInviteScheduledFuture.cancel(false);
         }

         if (this.trialAvailableScheduledFuture != null) {
            this.trialAvailableScheduledFuture.cancel(false);
         }

         if (this.liveStatsScheduledFuture != null) {
            this.liveStatsScheduledFuture.cancel(false);
         }

         if (this.unreadNewsScheduledFuture != null) {
            this.unreadNewsScheduledFuture.cancel(false);
         }
      } catch (Exception exception) {
         LOGGER.error("Failed to cancel Realms tasks", (Throwable)exception);
      }

   }

   private synchronized void setServers(List<RealmsServer> p_225080_1_) {
      int i = 0;

      for(RealmsServer realmsserver : this.removedServers) {
         if (p_225080_1_.remove(realmsserver)) {
            ++i;
         }
      }

      if (i == 0) {
         this.removedServers.clear();
      }

      this.servers = p_225080_1_;
   }

   public synchronized void removeItem(RealmsServer p_225085_1_) {
      this.servers.remove(p_225085_1_);
      this.removedServers.add(p_225085_1_);
   }

   private boolean isActive() {
      return !this.stopped;
   }

   @OnlyIn(Dist.CLIENT)
   class LiveStatsTask implements Runnable {
      private LiveStatsTask() {
      }

      public void run() {
         if (RealmsDataFetcher.this.isActive()) {
            this.getLiveStats();
         }

      }

      private void getLiveStats() {
         try {
            RealmsClient realmsclient = RealmsClient.create();
            RealmsDataFetcher.this.livestats = realmsclient.getLiveStats();
            RealmsDataFetcher.this.fetchStatus.put(RealmsDataFetcher.Task.LIVE_STATS, true);
         } catch (Exception exception) {
            RealmsDataFetcher.LOGGER.error("Couldn't get live stats", (Throwable)exception);
         }

      }
   }

   @OnlyIn(Dist.CLIENT)
   class PendingInviteUpdateTask implements Runnable {
      private PendingInviteUpdateTask() {
      }

      public void run() {
         if (RealmsDataFetcher.this.isActive()) {
            this.updatePendingInvites();
         }

      }

      private void updatePendingInvites() {
         try {
            RealmsClient realmsclient = RealmsClient.create();
            RealmsDataFetcher.this.pendingInvitesCount = realmsclient.pendingInvitesCount();
            RealmsDataFetcher.this.fetchStatus.put(RealmsDataFetcher.Task.PENDING_INVITE, true);
         } catch (Exception exception) {
            RealmsDataFetcher.LOGGER.error("Couldn't get pending invite count", (Throwable)exception);
         }

      }
   }

   @OnlyIn(Dist.CLIENT)
   class ServerListUpdateTask implements Runnable {
      private ServerListUpdateTask() {
      }

      public void run() {
         if (RealmsDataFetcher.this.isActive()) {
            this.updateServersList();
         }

      }

      private void updateServersList() {
         try {
            RealmsClient realmsclient = RealmsClient.create();
            List<RealmsServer> list = realmsclient.listWorlds().servers;
            if (list != null) {
               list.sort(new RealmsServer.ServerComparator(Minecraft.getInstance().getUser().getName()));
               RealmsDataFetcher.this.setServers(list);
               RealmsDataFetcher.this.fetchStatus.put(RealmsDataFetcher.Task.SERVER_LIST, true);
            } else {
               RealmsDataFetcher.LOGGER.warn("Realms server list was null or empty");
            }
         } catch (Exception exception) {
            RealmsDataFetcher.this.fetchStatus.put(RealmsDataFetcher.Task.SERVER_LIST, true);
            RealmsDataFetcher.LOGGER.error("Couldn't get server list", (Throwable)exception);
         }

      }
   }

   @OnlyIn(Dist.CLIENT)
   public static enum Task {
      SERVER_LIST,
      PENDING_INVITE,
      TRIAL_AVAILABLE,
      LIVE_STATS,
      UNREAD_NEWS;
   }

   @OnlyIn(Dist.CLIENT)
   class TrialAvailabilityTask implements Runnable {
      private TrialAvailabilityTask() {
      }

      public void run() {
         if (RealmsDataFetcher.this.isActive()) {
            this.getTrialAvailable();
         }

      }

      private void getTrialAvailable() {
         try {
            RealmsClient realmsclient = RealmsClient.create();
            RealmsDataFetcher.this.trialAvailable = realmsclient.trialAvailable();
            RealmsDataFetcher.this.fetchStatus.put(RealmsDataFetcher.Task.TRIAL_AVAILABLE, true);
         } catch (Exception exception) {
            RealmsDataFetcher.LOGGER.error("Couldn't get trial availability", (Throwable)exception);
         }

      }
   }

   @OnlyIn(Dist.CLIENT)
   class UnreadNewsTask implements Runnable {
      private UnreadNewsTask() {
      }

      public void run() {
         if (RealmsDataFetcher.this.isActive()) {
            this.getUnreadNews();
         }

      }

      private void getUnreadNews() {
         try {
            RealmsClient realmsclient = RealmsClient.create();
            RealmsNews realmsnews = null;

            try {
               realmsnews = realmsclient.getNews();
            } catch (Exception exception) {
            }

            RealmsPersistence.RealmsPersistenceData realmspersistence$realmspersistencedata = RealmsPersistence.readFile();
            if (realmsnews != null) {
               String s = realmsnews.newsLink;
               if (s != null && !s.equals(realmspersistence$realmspersistencedata.newsLink)) {
                  realmspersistence$realmspersistencedata.hasUnreadNews = true;
                  realmspersistence$realmspersistencedata.newsLink = s;
                  RealmsPersistence.writeFile(realmspersistence$realmspersistencedata);
               }
            }

            RealmsDataFetcher.this.hasUnreadNews = realmspersistence$realmspersistencedata.hasUnreadNews;
            RealmsDataFetcher.this.newsLink = realmspersistence$realmspersistencedata.newsLink;
            RealmsDataFetcher.this.fetchStatus.put(RealmsDataFetcher.Task.UNREAD_NEWS, true);
         } catch (Exception exception1) {
            RealmsDataFetcher.LOGGER.error("Couldn't get unread news", (Throwable)exception1);
         }

      }
   }
}
