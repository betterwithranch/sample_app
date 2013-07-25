(ns sample-app.root-spec
  (:use
    [speclj.core]
    [joodo.spec-helpers.controller]
    [sample-app.root]))

(describe "sample-app"

  (with-mock-rendering)
  (with-routes app-handler)

  (it "handles home page"
    (let [result (do-get "/")]
      (should= 200 (:status result))
      (should= "index" @rendered-template)))
  )

(run-specs)
