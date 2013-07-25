(doctype :html5)
[:html
 [:head
  [:meta {:http-equiv "Content-Type" :content "text/html" :charset "iso-8859-1"}]
  [:title "sample-app"]
  (include-css "/stylesheets/sample_app.css")
  (include-js "/javascript/sample_app.js")]
 [:body
  [:img { :src "/images/WED-logo.png" }]
  (eval (:template-body joodo.views/*view-context*))
]]
