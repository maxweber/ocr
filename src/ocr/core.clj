(ns ocr.core
  (:require [clojure.string :as s]))

(def numbers
" _     _  _     _  _  _  _  _ 
| |  | _| _||_||_ |_   ||_||_|
|_|  ||_  _|  | _||_|  ||_| _|")

(defn split-numbers [numbers]
  (let [lines (s/split-lines numbers)
        number-count (/ (count (first lines)) 3)
        split (map (partial partition 3) lines)]
    (map (fn [n]
           (map #(nth % n) split)) (range number-count))
    ))

(defn build-numbers []
  (into {}
        (map (fn [number-picture n]
               [number-picture n]) (split-numbers numbers) (range 10))))

(def patterns (build-numbers))

(def test-number
"    _  _  _  _  _  _     _ 
|_||_|| || ||_   |  |  ||_ 
  | _||_||_||_|  |  |  | _| ")

(defn resolve-number [input]
  (apply str (map patterns (split-numbers input))))
