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
  (let [A (parse-int (read-line))
        B  (parse-int (read-line))
        C (parse-int (read-line))
        X (parse-int (read-line))]
    [A B C X]))

(defn solve [[A B C X]]
  (for [a (map (partial * 500) (range (inc A)))
        b (map (partial * 100) (range (inc B)))
        c (map (partial * 50) (range (inc C)))
        :when (= (+ a b c) X)]
    [a b c]))

(defn output [comb]
  (println (format "%d" (count comb))))

(defn -main [& args]
  (-> {}
      ->input-model
      solve
      output))

(-main)
