(ns c-style.core
  "A library bringing some c-style syntax to clojure.")

(defn- var-expr?
  "Check experssion is a variable definition."
  [expr]
  (and (list? expr) (= :let (first expr))))

(defn- convert-expr
  "Convert expression to let first form."
  [expr]
  (if (var-expr? expr)
    [(second expr) (nth expr 2)]
    ['_ expr]))

(defn- nest-let
  "Recursive let form."
  [exprs]
  (if (= 1 (count exprs))
    (let [first-expr (first exprs)]
      (if (var-expr? first-expr) nil first-expr))
    (list 'let (convert-expr (first exprs)) (nest-let (rest exprs)))))

(defmacro proc
  "Allow C-Style variable definition.

  (proc (:let a 1)
        (:let [b & _] [2 3 4])
        (+ a b)) ;; => 3
  "
  [& exprs]
  (when exprs (nest-let exprs)))

;; (defmacro if-map
;;   "")

;; (if-map [:condition1 [false "message1"]
;;            :condition2 [false "message2"]
;;            :condition3 [false "message3"]]
;;           [true "ok"])
