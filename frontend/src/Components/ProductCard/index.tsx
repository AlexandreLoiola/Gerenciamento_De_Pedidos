import React from 'react'

import { ProductCardContainer, LeftSide, Title, Price, BuyButton, Description, RightSide } from './styles';

interface IProps {
  title: string;
  description: string;
  price: number;
};

const ProductCard: React.FC<IProps> = ({ title, description, price }) => {
  return (
    <ProductCardContainer>
      <LeftSide>
        <Title>{title}</Title>
        <Description>{description}</Description>
      </LeftSide>
      <RightSide>
        <Price>${price}</Price>
        <BuyButton>COMPRAR</BuyButton>
      </RightSide>
    </ProductCardContainer>
  );
};

export default ProductCard;