(ns c-style-let.core)

(defmacro code-block
  "C-Style variable definition."
  [& exprs]
  (when exprs
    (let [[exprs# [last-expr#]]
          (split-at (- (count exprs) 1) exprs)

          pairs#
          (for [expr# exprs#]
            (if (and (list? expr#) (= (first expr#) :var))
              [(second expr#) (nth expr# 2)]
              ['_ expr#]))]

      (nest-let pairs# (if (= :var (first last-expr#))
                         nil
                         last-expr#)))))

(defn- nest-let [exprs last-expr]
  (if (empty? exprs)
    last-expr
    (list 'let (first exprs) (nest-let (rest exprs) last-expr))))

