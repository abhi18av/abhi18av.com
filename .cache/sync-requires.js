// prefer default export if available
const preferDefault = m => m && m.default || m


exports.layouts = {

}

exports.components = {
  "component---src-pages-index-js": preferDefault(require("/Users/eklavya/projects/portfolio/abhi18av.com/src/pages/index.js"))
}

exports.json = {
  "index.json": require("/Users/eklavya/projects/portfolio/abhi18av.com/.cache/json/index.json")
}