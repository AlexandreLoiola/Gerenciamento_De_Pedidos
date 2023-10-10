import React, { useState, useEffect } from "react";
import Modal from "react-bootstrap/Modal";
import { Button } from "react-bootstrap";
import { StyledModal, StyledOrderItem, StyledParagraph } from "./styles";
import axios from "axios";
import moment from 'moment';

interface Order {
  customer: string;
  dateTime: string;
  orderNumber: number;
  price: number;
  seller: string;
  status: string;
}

const ViewOrder: React.FC<{
  show: boolean;
  order: Order | null;
  handleClose: () => void;
}> = ({ show, order, handleClose }) => {
  const [orderItems, setOrderItems] = useState([]);

  useEffect(() => {
    if (order) {
      axios
        .get(
          `http://localhost:8080/api/management/orderItems/${order.orderNumber}`
        )
        .then((response) => {
          setOrderItems(response.data);
        })
        .catch((error) => {
          console.error("Erro ao buscar itens do pedido:", error);
        });
    }
  }, [order]);

  return (
    <>
      <StyledModal show={show} onHide={handleClose} centered>
        <Modal.Header closeButton>
          <Modal.Title style={{ color: "#0dcaf", fontWeight: "700" }}>
            Detalhes do Pedido
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {order && (
            <div>
              <StyledParagraph><b>Número do Pedido: </b>{order.orderNumber}</StyledParagraph>
              <StyledParagraph><b>Data e Hora: </b>{moment(order.dateTime.split('.')[0]).format('DD/MM/YYYY HH:mm:ss')}</StyledParagraph>
              <StyledParagraph><b>Preço: </b>R$ {order.price}</StyledParagraph>
              <StyledParagraph><b>Status: </b>{order.status}</StyledParagraph>
              <hr></hr>
            </div>
          )}
          <h5>
            <b>Itens do Pedido:</b>
          </h5>
          <div style={{ maxHeight: "100px", overflowY: "auto" }}>
            {orderItems.map((item: any, index) => (
              <StyledOrderItem key={index}>
                <span>• {item.productName}</span>
                <span>{item.quantity} unidade(s)</span>
              </StyledOrderItem>
            ))}
          </div>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="success" onClick={handleClose}>
            Fechar
          </Button>
        </Modal.Footer>
      </StyledModal>
    </>
  );
};

export default ViewOrder;
