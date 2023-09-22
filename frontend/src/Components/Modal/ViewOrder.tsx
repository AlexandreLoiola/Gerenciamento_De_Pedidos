import React, { useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import {Button} from 'react-bootstrap';
import {StyledModal} from './styles'

interface Order {
  customer: string;
  dateTime: string;
  orderNumber: number;
  price: number;
  seller: string;
  status: string;
}

const ViewOrder: React.FC<{ show: boolean; order: Order | null; handleClose: () => void }> = ({ show, order, handleClose }) => {
  
  return (
    <>
      <StyledModal show={show} onHide={handleClose} centered>
        <Modal.Header closeButton>
          <Modal.Title>Detalhes do Pedido</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {order && (
            <p>
              <p>Número do Pedido: {order.orderNumber}</p>
              <p>Data e Hora: {order.dateTime}</p>
              <p>Cliente: {order.customer}</p>
              <p>Vendedor: {order.seller}</p>
              <p>Preço: {order.price}</p>
              <p>Status: {order.status}</p>
            </p>
          )}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Fechar
          </Button>
        </Modal.Footer>
      </StyledModal>
    </>
  );
};

export default ViewOrder;
