import React from "react";

import { useNavigate } from "react-router-dom";

import {
  ProductCardContainer,
  LeftSide,
  Title,
  Price,
  Description,
  RightSide,
} from "./styles";
import SubmitButton from "../SubmitButton";

interface IProps {
  title: string;
  description: string;
  price: number;
  addToCart: (item: ICartItem) => void;
}

interface ICartItem {
  nameProduct: string;
  unitPrice: number;
  quantity: number;
}

const ProductCard: React.FC<IProps> = ({
  title,
  description,
  price,
}) => {
  const handleBuy = () => {
    const newItem: ICartItem = {
      nameProduct: title,
      unitPrice: price,
      quantity: 1,
    };
  };

  const navigate = useNavigate();

  const handleGoToCart = () => {

  };

  return (
    <ProductCardContainer>
      <LeftSide>
        <Title>{title}</Title>
        <Description>{description}</Description>
      </LeftSide>
      <RightSide>
        <Price>${price}</Price>
        <SubmitButton onClick={handleBuy} title={"Adicionar ao carrinho"} />
        {/* <input value={stock}} type="number" id="price" name="price" step="0.01" /> */}
      </RightSide>
    </ProductCardContainer>
  );
};

export default ProductCard;
