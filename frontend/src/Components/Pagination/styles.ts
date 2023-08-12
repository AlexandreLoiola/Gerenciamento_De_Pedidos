import styled from "styled-components";
import { Pagination } from "react-bootstrap";

export const StyledPagination = styled(Pagination)`
  display: flex;
  justify-content: center;

  .page-item.active .page-link {
    background-color: green;
    border-color: green;
  }

  .page-item:not(.active) .page-link {
    color: gray;
  }

  .page-item.active .page-link {
    color: white;
  }
`;