;; utilities ---------
(defn split-by-keyword
  ([line]
   (split-by-keyword line #" "))
  ([line split-keyword]
   (clojure.string/split line split-keyword)))

(defn trim [line]
  (clojure.string/trim line))

(defn parse-int [str-int]
  (Integer/parseInt str-int))
;; ---------------------

(defn ->input-model [_]
  (let [n (parse-int (read-line))
        ds (map #(-> % trim parse-int) (for [_ (range n)] (read-line)))]
    [n ds]))

(defn solve [[n ds]]
  (sort (set ds)))

(defn output [mochis]
  (println (format "%d" (count mochis))))

(defn -main [& args]
  (-> {}
      ->input-model
      solve
      output))

(-main)
