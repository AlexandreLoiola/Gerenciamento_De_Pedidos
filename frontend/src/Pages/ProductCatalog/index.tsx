import React, { ChangeEvent, useState, useEffect } from "react";

import MainHeader from "../../Components/Header";
import ProductCard from "../../Components/ProductCard";
import axios from "axios";

import {
  FormInput,
  SearchFormInput,
} from "./styles";
import SubmitButton from "../../Components/SubmitButton";

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
  const [identifier, setIdentifier] = useState("");


  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await axios.get<IProduct[]>(`http://localhost:8080/api/management/products/${identifier}`);
      setProducts(response.data);
    } catch (error) {
      console.error("AXIOS ERROR: ", error);
    }
  };

  return (
    <>
      <MainHeader title={"Catálogo de Produtos"} />

      <form>
          <SearchFormInput>
            <FormInput
              type="text"
              value={identifier}
              onChange={(event: ChangeEvent<HTMLInputElement>) =>
                setIdentifier(event.target.value)
              }
              placeholder="Identificador"
            />
            <SubmitButton
              onClick={fetchProducts}
              style={{ backgroundColor: "#4169E1" }}
              title={"BUSCAR"}
            />
          </SearchFormInput>
        </form>

      {products.length === 0 ? (
        <h1>Não há produtos no Catálogo</h1>
      ) : (
        products.map((product) => (
          <ProductCard
            key={product.name}
            title={product.name}
            description={product.description}
            price={product.unitPrice}
            addToCart={() => {}}
          />
        ))
      )}
    </>
  );
};

export default ProductCatalog;
