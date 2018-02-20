(defproject c-style "0.2.0"
  :description "A library bringing some c-style syntax to clojure."
  :url "http://github.com/jmjoy/c-style"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :plugins [[lein-codox "0.10.3"]]
  :codox {:output-path "docs"
          :metadata {:doc/format :markdown}
          :source-uri "https://github.com/jmjoy/c-style/blob/{version}/{filepath}#L{line}"})
