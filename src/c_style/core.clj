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
  "Proc is a macro like `do`, but allow C-Style variable definition.
  The form started with `:let` keyword is the variable definition,
  and the variable scope is inside `proc`.

  Example:
  ```
  (proc (:let a 1)
        (:let [b & _] [2 3 4])
        (+ a b)) ;; => 3
  ```"
  [& exprs]
  (when exprs (nest-let exprs)))

(defmacro cond-not
  "Cond-not is relative to `cond` like `if` relative to `if-not`.

  Example:
  ```
  (let [grade 85]
         (cond-not
           (<= grade 90) \"A\"
           (<= grade 80) \"B\"
           (<= grade 70) \"C\"
           (<= grade 60) \"D\"
           false \"F\")) ;; => \"B\"
  ```"
  [& clauses]
  (when clauses
    (let [clauses
          (map (fn [i clause]
                 (if (even? i) `(not ~clause) clause))
               (range)
               clauses)]
      `(cond ~@clauses))))

