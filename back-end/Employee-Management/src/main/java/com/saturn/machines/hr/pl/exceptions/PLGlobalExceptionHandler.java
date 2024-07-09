//package com.saturn.machines.hr.pl.exceptions;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import com.saturn.machines.hr.bl.exceptions.BLException;
//
//@ControllerAdvice
//public class PLGlobalExceptionHandler {
//
//    @ExceptionHandler(BLException.class)
//    public ResponseEntity<?> handleBLException(BLException ex) {
//        if (ex.hasGenericException()) {
//            return ResponseEntity.badRequest().body("Generic Exception: " + ex.getGenericException());
//        } else if (ex.hasException("title") || ex.hasException("code")) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: " + ex.getException("title"));
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + ex.getMessage());
//        }
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleException(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
//    }
//}
