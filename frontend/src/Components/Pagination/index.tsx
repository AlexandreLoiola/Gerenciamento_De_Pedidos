import React from 'react';
import { Pagination } from 'react-bootstrap';
import { StyledPagination } from './styles';

interface MyPaginationProps {
  currentPage: number;
  totalPages: number;
  onPageChange: (pageNumber: number) => void;
}

const MyPagination: React.FC<MyPaginationProps> = ({ currentPage, totalPages, onPageChange }) => {
  let items = [];
  for (let number = 1; number <= totalPages; number++) {
    items.push(
      <Pagination.Item key={number} active={number === currentPage} onClick={() => onPageChange(number)}>
        {number}
      </Pagination.Item>,
    );
  }

  return (
    <div>
      <StyledPagination>{items}</StyledPagination>
    </div>
  );
};

export default MyPagination;