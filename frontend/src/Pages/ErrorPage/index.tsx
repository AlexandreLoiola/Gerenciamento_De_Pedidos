import React from "react";
import MyPagination from "../../Components/Pagination";

const ErrorPage: React.FC = () => {
  return (
    <>
      <h1>Página 404</h1>
      <MyPagination
        currentPage={1}
        totalPages={3}
        onPageChange={function (pageNumber: number): void {
          throw new Error("Function not implemented.");
        }}
      />
    </>
  );
};

export default ErrorPage;
