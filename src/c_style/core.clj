(ns c-style.core)

(defn- nest-let [exprs last-expr]
  "Recursive let form."
  (if (empty? exprs)
    last-expr
    (list 'let (first exprs) (nest-let (rest exprs) last-expr))))

(defmacro do++
  "Allow C-Style variable definition."
  [& exprs]
  (when exprs
    (let [[exprs [last-expr]]
          (split-at (- (count exprs) 1) exprs)

          pairs
          (for [expr exprs]
            (if (and (list? expr) (= (first expr) :let))
              [(second expr) (nth expr 2)]
              ['_ expr]))]

      (nest-let pairs (if (and (list? last-expr) (= :let (first last-expr)))
                         nil
                         last-expr)))))

