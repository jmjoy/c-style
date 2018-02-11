# c-style-let

A library bringing some c-style syntax to clojure.

## Install

With Leiningen/Boot:

```clojure
[c-style "0.1.0"]
```

## Usage

```clojure
(require '[c-style.core :refer :all])

;; The `do++` marco help you defined variables like c-family language.
(do++
 (:let a 1)
 (:let [b & _] [2 3 4])
 (+ a b)) ;; => 3

```

## License

Copyright © 2018 ____JM_Joy____

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
