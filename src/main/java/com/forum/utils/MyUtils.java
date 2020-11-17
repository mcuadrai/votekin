package com.forum.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forum.domain.User;

@Component
public class MyUtils {
	
	private static MessageSource messageSource;
	
	public MyUtils(MessageSource messageSource) {
		MyUtils.messageSource = messageSource;
	}

	
	public static String getMessage(String messageKey, Object... args) {
		// Obtiene el locale del computador.
		return messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
	}
	
	public static String getMessage(String messageKey, Object[] args, Locale locale) {
		return messageSource.getMessage(messageKey, args, locale);
	}
	
    
	public static void flash(RedirectAttributes redirectAttributes, String flashKind, String flashMessageCode) {
		redirectAttributes.addFlashAttribute(flashKind,getMessage(flashMessageCode));
	}
	
	public static void sendViewMessage(Model model, String kind, String messageCode) {
		model.addAttribute(kind,getMessage(messageCode));
	}

	
	
	
	
	public static Optional<User> currentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			
			Object principal = auth.getPrincipal();
			
			if (principal instanceof User)
				return Optional.of((User) principal);
		}
		//
		
		return Optional.empty();
	}
		
//		public static Optional<User> currentUser() {
//            User user = new User();
//            user.setId((long)2);
//			return Optional.of((User) user);
//		}
		
//	
	public static void login(UserDetails user) {
		
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	public static void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
	public static void afterCommit(Runnable runnable) {
		
		TransactionSynchronizationManager.registerSynchronization(
				new TransactionSynchronizationAdapter() {
				    @Override
				    public void afterCommit() {
				    	
						runnable.run();
				    }
			    }
			);	
	}
	
	public static void validate(boolean valid, String messageKey, Object ... messageArgs) {
		
		if (!valid)
			throw new RuntimeException(MyUtils.getMessage(messageKey, messageArgs));
	}
	
    public static void validate(boolean valid, String messageKey, Object[] messageArgs, Locale locale) {
		
		if (!valid)
			throw new RuntimeException(MyUtils.getMessage(messageKey, messageArgs, locale));
	}
	
	
	
	
	
	public static String  convertTimeToTimePoint (LocalDateTime localDate) {
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime pastTime =  localDate;//convertDateToLocalDateTime(date);
		
		long elapsed = ChronoUnit.HOURS.between(pastTime, currentTime);
		if (elapsed > 24) {
			elapsed = ChronoUnit.DAYS.between(pastTime, currentTime);
			if (elapsed > 31) {
				elapsed = ChronoUnit.MONTHS.between(pastTime, currentTime);
				if (elapsed > 12) {
					elapsed = ChronoUnit.YEARS.between(pastTime, currentTime);
				}
			}
		}
		return elapsed + "ago";
	}

	
	public static LocalDateTime convertDateToLocalDateTime(Date date) {

        //Asia/Kuala_Lumpur +8
        ZoneId defaultZoneId = ZoneId.systemDefault();
        System.out.println("System Default TimeZone : " + defaultZoneId);

        //1. Convert Date -> Instant
        Instant instant = date.toInstant();
        System.out.println("instant : " + instant); //Zone : UTC+0

//        //2. Instant + system default time zone + toLocalDate() = LocalDate
//        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
//        System.out.println("localDate : " + localDate);

        //3. Instant + system default time zone + toLocalDateTime() = LocalDateTime
        LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
        System.out.println("localDateTime : " + localDateTime);
		return localDateTime;

//        //4. Instant + system default time zone = ZonedDateTime
//        ZonedDateTime zonedDateTime = instant.atZone(defaultZoneId);
//        System.out.println("zonedDateTime : " + zonedDateTime);

    }
	
	

	
	public static String UNVERIFIED = "UNVERIFIED";
	public static String VERIFIED   = "VERIFIED";
	public static String BLOCKED    = "BLOCKED";	
	
	
	
	
	
	
}
