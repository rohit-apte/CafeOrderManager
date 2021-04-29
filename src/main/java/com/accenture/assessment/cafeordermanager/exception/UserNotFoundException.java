package com.accenture.assessment.cafeordermanager.exception;

public class UserNotFoundException extends Exception {
  private static final String message = "No user found for %s";

  public UserNotFoundException(final String username) {
    super(String.format(message, username));
  }
}
