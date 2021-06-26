// https://github.com/michael-ciniawsky/postcss-load-config
const autoprefixer = require('autoprefixer')
const atimport = require('postcss-import')
const cssnano = require('cssnano')
module.exports = {
  plugins:[
    atimport,
    autoprefixer({
      browers:['last 2 version']
    }),
    cssnano
  ]
}
