#lang plai

;;;; CS214 Grad Lab 1: Church Numerals

;;; Pairs
(define make-pair
  (lambda (a b)
    (lambda (selector)
      (selector a b))))

(define left
  (lambda (procedure)
    (procedure (lambda (a b) a))))

(define right
  (lambda (procedure)
    (procedure (lambda (a b) b))))

;;; Church Numerals
(define zero
  (lambda (f)
    (lambda (x)
      x)))

(define one
  (lambda (f)
    (lambda (x)
      (f x))))

(define two
  (lambda (f)
    (lambda (x)
      (f (f x)))))

(define three
  (lambda (f)
    (lambda (x)
      (f (f (f x))))))

;;; Arithmetic
(define add-one
  (lambda (x)
    (+ x 1)))

(define double
  (lambda (x)
    (* x 2)))

(define triple
  (lambda (x)
    (* x (* x x))))

;;; Addition
(define succ
  (lambda (cn)
    (lambda (f)
      (lambda (x)
        (f ((cn f) x))))))

(define sum1
  (lambda (cn1)
    (lambda (cn2)
      (lambda (f)
        (lambda (x)
          ((cn1 f)
           ((cn2 f) x)))))))

(define sum2
  (lambda (cn1)
    (lambda (cn2)
      ((cn1 succ) cn2))))

;;; Multiplication
(define product
  (lambda (cn1)
    (lambda (cn2)
      ((cn1 (sum1 cn2)) zero))))

;;; Subtraction
(define f1
  (lambda (p)
    (make-pair (right p)
               (succ (right p)))))

(define pred
  (lambda (cn)
    (left ((cn f1)
           (make-pair zero zero)))))

;;; 1. Convert a Church numeral into an ordinary Scheme number
(define CN->num
  (lambda (CN)
      ((CN add-one) 0)))

;;; 2. Convert a Scheme number greater than or equal to 0 into a Church numeral
(define num->CN
  (lambda (num)
    (lambda (f)
      (lambda (x)
        (if (= num 0) x
            (((num->CN (- num 1)) f) ((one f) x)))))))

;;; 3. Implement subtraction of Church Numerals
(define subtract
  (lambda (cn1)
    (lambda (cn2)
      ((cn2 pred) cn1))))

;;; Tests of succ
;(test (CN->num (succ zero)) 1)
;(test (CN->num (succ two)) 3)

;;; Tests of sum1
;(test (CN->num ((sum1 zero) zero)) 0)
;(test (CN->num ((sum1 one) one)) 2)
;(test (CN->num ((sum1 one) two)) 3)
;(test (CN->num ((sum1 two) two)) 4)

;;; Tests of sum2
;(test (CN->num ((sum2 zero) zero)) 0)
;(test (CN->num ((sum2 one) one)) 2)
;(test (CN->num ((sum2 one) two)) 3)
;(test (CN->num ((sum2 two) two)) 4)

;;; Tests of product
;(test(CN->num ((product zero) zero)) 0)
;(test(CN->num ((product zero) two)) 0)
;(test(CN->num ((product two) zero)) 0)
;(test(CN->num ((product two) one)) 2)
;(test(CN->num ((product two) two)) 4)
;(test(CN->num ((product two) three)) 6)

;;; Test of pred
;(test (CN->num (pred two)) 1)
;(test (CN->num (pred one)) 0)