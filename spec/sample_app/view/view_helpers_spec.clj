(ns sample-app.view.view-helpers-spec
  (:require [speclj.core :refer [ describe it should= run-specs around]]
            [sample-app.view.view-helpers :refer [get-post-name]]))

(describe "view_helpers"
          (it "gets the title of a post"
              (should= "test post 1"
                       (get-post-name "20111215_test-post-1.hiccup"))))

