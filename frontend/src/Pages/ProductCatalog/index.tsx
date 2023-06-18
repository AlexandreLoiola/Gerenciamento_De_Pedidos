import React, { useState, useEffect } from "react";

import MainHeader from "../../Components/Header";
import ProductCard from "../../Components/ProductCard";
import axios from "axios";

interface IProduct {
  name: string;
  description: string;
  unitPrice: number;
}

export interface ICartItem {
  nameProduct: string;
  unitPrice: number;
  quantity: number;
}

const ProductCatalog: React.FC = () => {
  const [products, setProducts] = useState<IProduct[]>([]);
  const [cartItems, setCartItems] = useState<ICartItem[]>([]);



  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await axios.get<IProduct[]>("http://localhost:8080/api/management/products");
      setProducts(response.data);
    } catch (error) {
      console.error("AXIOS ERROR: ", error);
    }
  };

  const addToCart = (item: ICartItem) => {
    setCartItems([...cartItems, item]);
  };

  return (
    <>
      <MainHeader title={"Catálogo de Produtos"} />

      {products.length === 0 ? (
        <h1>Não há produtos no Catálogo</h1>
      ) : (
        products.map((product) => (
          <ProductCard
            key={product.name}
            title={product.name}
            description={product.description}
            price={product.unitPrice}
            addToCart={addToCart}
          />
        ))
      )}
    </>
  );
};

export default ProductCatalog;
