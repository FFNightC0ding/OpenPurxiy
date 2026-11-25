package dev.purxiy.profile;

import java.time.LocalDate;
import java.util.Map.Entry;

final class ProfileSummary {
   final ProfileDay data;
   final LocalDate today;
   final ProfileLoader lifetime;
   final ProfileLoader week;
   final ProfileLoader lastWeek;

   final ProfileDay.DayCache live;
   final int streak;
   final int bestStreak;
   final int activeDays;
   final int level;
   final double levelProgress;
   final double toNextLevel;

   final ProfileTotals.TotalKind archetype;

   final ProfileDay.DayCache longest;

   final Entry<LocalDate, ProfileLoader> busiest;

   final ProfileDay.DayRecord favourite;

   private ProfileSummary(ProfileDay var1, LocalDate var2,  ProfileDay.DayCache var3) {
      this.data = var1;
      this.today = var2;
      this.live = var3;
      this.lifetime = ProfileTotals.lifetime(var1);
      this.week = ProfileTotals.range(var1, var2, 7);
      this.lastWeek = ProfileTotals.range(var1, var2.minusDays(7L), 7);
      this.streak = ProfileTotals.streak(var1, var2);
      this.bestStreak = Math.max(this.streak, ProfileTotals.bestStreak(var1));
      this.activeDays = ProfileTotals.activeDays(var1);
      this.level = ProfileTotals.level(this.lifetime.play);
      this.levelProgress = ProfileTotals.levelProgress(this.lifetime.play);
      this.toNextLevel = ProfileTotals.levelStart(this.level + 1) - this.lifetime.play;
      this.archetype = ProfileTotals.archetype(this.lifetime);
      this.longest = ProfileTotals.longest(var1.sessions, var3);
      this.busiest = ProfileTotals.busiest(var1, ProfileKind.PLAYTIME);
      this.favourite = ProfileTotals.favourite(var1);
   }

   static ProfileSummary take() {
      return new ProfileSummary(ProfileSupport.data(), ProfileSupport.today(), ProfileSupport.live());
   }

   double trend(ProfileKind var1) {
      double var2 = var1.of(this.week);
      double var4 = var1.of(this.lastWeek);
      return var4 <= 0.0 ? Double.NaN : (var2 - var4) / var4;
   }
}
