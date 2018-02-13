(ns c-style.core)

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

(defmacro do++
  "Allow C-Style variable definition."
  [& exprs]
  (when exprs (nest-let exprs)))
