import styled from "styled-components";
import Modal from 'react-bootstrap/Modal';

export const StyledModal = styled(Modal)`
  .modal-content {
    background-color: #f8f9fa;
    color: #343a40;
  }
`;

export const StyledOrderItem = styled.div`
  display: flex;
  justify-content: space-between;
  margin-right: 10px;
`;

export const StyledParagraph = styled.p`
margin: 5px;
`;
