import React, { useState, useEffect } from "react";
import MainHeader from "../../Components/Header";
import ProductCard from "../../Components/ProductCard";
import axios from "axios";

interface IProduct {
  name: string;
  description: string;
  unitPrice: number;
}

const ProductCatalog: React.FC = () => {
  const [products, setProducts] = useState<IProduct[]>([]);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      await axios
        .get<IProduct[]>("http://localhost:8080/api/management/products")
        .then((response) => {
          setProducts(response.data);
        })
        .catch((error) => {
          console.error("AXIOS ERROR : ", error);
        });
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <MainHeader title={"Catálogo de Produtos"} />

      { products.length === 0 ?  (
        <h1>Não há produtos no Catálogo</h1>
      ) : (
        products.map((product) => (
          <ProductCard
            title={product.name}
            description={product.description}
            price={product.unitPrice}
          />
        ))
    )
}
    </>
  );
};

export default ProductCatalog;
