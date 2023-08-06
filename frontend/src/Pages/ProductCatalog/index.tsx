import React, { ChangeEvent, useState, FormEvent } from "react";
import MainHeader from "../../Components/Header";
import ProductCard from "../../Components/ProductCard";
import axios from "axios";
import { FormInput, SearchFormInput } from "./styles";
import SubmitButton from "../../Components/SubmitButton";

interface IProduct {
  name: string;
  description: string;
  unitPrice: number;
}

const ProductCatalog: React.FC = () => {
  const [products, setProducts] = useState<IProduct[]>([]);
  const [identifier, setIdentifier] = useState("");

  const handleFetch = async (id: string) => {
    try {
      await axios
        .get(`http://localhost:8080/api/management/products/${id}`)
        .then((response) => {
          setIdentifier("");

          Array.isArray(response.data)
            ? setProducts(response.data)
            : setProducts([response.data]);

        })
        .catch((error) => {
          alert(error.response.data.message);
        });
    } catch (error) {
      console.error(error);
    }
  };

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
  };

  return (
    <>
      <MainHeader title={"Catálogo de Produtos"} />

      <form onSubmit={handleSubmit}>
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
            onClick={() => handleFetch(identifier)}
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