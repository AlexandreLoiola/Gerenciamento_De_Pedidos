import React, { useState } from "react";
import MainHeader from "../../Components/Header";
import { ICartItem } from "../ProductCatalog";

const CartPage: React.FC = () => {
  const [cartItems, setCartItems] = useState<ICartItem[]>([]);

  const handleRemoveItem = (index: number) => {
    const updatedCartItems = [...cartItems];
    updatedCartItems.splice(index, 1);
    setCartItems(updatedCartItems);
  };

  return (
    <>
      <MainHeader title={"Carrinho"} />
      {cartItems.length === 0 ? (
        <h1>Não há itens no carrinho</h1>
      ) : (
        <div>
          {cartItems.map((item, index) => (
            <CartItem
              key={index}
              item={item}
              onRemove={() => handleRemoveItem(index)}
            />
          ))}
        </div>
      )}
    </>
  );
};

interface ICartItemProps {
  item: ICartItem;
  onRemove: () => void;
}

const CartItem: React.FC<ICartItemProps> = ({ item, onRemove }) => {
  return (
    <div>
      <h3>{item.nameProduct}</h3>
      <p>Preço: ${item.unitPrice}</p>
      <p>Quantidade: {item.quantity}</p>
      <button onClick={onRemove}>Remover</button>
    </div>
  );
};

interface IProps {
  title: string;
  description: string;
  price: number;
  onAddToCart: () => void;
}

export default CartPage;
