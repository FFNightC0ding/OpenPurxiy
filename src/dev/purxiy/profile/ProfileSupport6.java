// Sourced By DevFFNightmare
package dev.purxiy.profile;

import java.time.LocalDate;
import java.util.Map.Entry;

final class ProfileSupport6 {
   final ProfileLoader2 data;
   final LocalDate today;
   final ProfileLoader lifetime;
   final ProfileLoader week;
   final ProfileLoader lastWeek;
   
   final ProfileLoader2.TypeW live;
   final int streak;
   final int bestStreak;
   final int activeDays;
   final int level;
   final double levelProgress;
   final double toNextLevel;
   
   final ProfileSupport3.TypeF archetype;
   
   final ProfileLoader2.TypeW longest;
   
   final Entry<LocalDate, ProfileLoader> busiest;
   
   final ProfileLoader2.TypeD favourite;

   private ProfileSupport6(ProfileLoader2 var1, LocalDate var2,  ProfileLoader2.TypeW var3) {
      this.data = var1;
      this.today = var2;
      this.live = var3;
      this.lifetime = ProfileSupport3.lifetime(var1);
      this.week = ProfileSupport3.range(var1, var2, 7);
      this.lastWeek = ProfileSupport3.range(var1, var2.minusDays(7L), 7);
      this.streak = ProfileSupport3.streak(var1, var2);
      this.bestStreak = Math.max(this.streak, ProfileSupport3.bestStreak(var1));
      this.activeDays = ProfileSupport3.activeDays(var1);
      this.level = ProfileSupport3.level(this.lifetime.play);
      this.levelProgress = ProfileSupport3.levelProgress(this.lifetime.play);
      this.toNextLevel = ProfileSupport3.levelStart(this.level + 1) - this.lifetime.play;
      this.archetype = ProfileSupport3.archetype(this.lifetime);
      this.longest = ProfileSupport3.longest(var1.sessions, var3);
      this.busiest = ProfileSupport3.busiest(var1, ProfileKind.PLAYTIME);
      this.favourite = ProfileSupport3.favourite(var1);
   }

   static ProfileSupport6 take() {
      return new ProfileSupport6(ProfileSupport.data(), ProfileSupport.today(), ProfileSupport.live());
   }

   double trend(ProfileKind var1) {
      double var2 = var1.of(this.week);
      double var4 = var1.of(this.lastWeek);
      return var4 <= 0.0 ? Double.NaN : (var2 - var4) / var4;
   }
}
