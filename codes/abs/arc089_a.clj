;; utilities ---------
(defn split-by-keyword
  ([line]
   (split-by-keyword line #" "))
  ([line split-keyword]
   (clojure.string/split line split-keyword)))

(defn parse-int [str-int]
  (Integer/parseInt str-int))

(defn abs [x] (if (neg? x) (- x) x))
;; ---------------------

(defn ->input-model [_]
  (let [time-pos-list (rest (line-seq (clojure.java.io/reader *in*)))]
    [_ time-pos-list]))

(defn solve [[_ time-pos-list]]
  (loop [[ti xi yi] [0 0 0]
         _time-pos-list time-pos-list]
    (if (zero? (count _time-pos-list)) true
        (let [pi+1 (->> (first _time-pos-list) split-by-keyword (map parse-int))
              [dt dx dy] (let [[ti+1 xi+1 yi+1] pi+1]
                           [(- ti+1 ti) (abs (- xi+1 xi)) (abs (- yi+1 yi))])]
          (when (zero? (mod dt (+ dx dy)))
            (recur pi+1 (rest _time-pos-list)))))))

(defn output [reach?]
  (println (if reach? "Yes" "No")))

(defn -main [& args]
  (-> {}
      ->input-model
      solve
      output))

(-main)
