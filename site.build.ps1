task BuildQuarto {
  quarto render blog/quarto/*
  mv blog/quarto/*.md blog/
}
