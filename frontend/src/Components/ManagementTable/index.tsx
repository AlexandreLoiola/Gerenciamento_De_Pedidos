import React from "react";
import { Table } from "react-bootstrap";
import TableToggle from "./TableToggle";
import {
  PencilIcon,
  ThrashIcon,
  EyeIcon,
  StyledButton,
} from "./styles";
import { useNavigate } from "react-router-dom";
import CartQuantityUpdater from "./CartQuantityUpdater";

interface IDataType {
  [key: string]: any;
}

interface IProps {
  columnTitles: string[];
  data: IDataType[];
  objectKeys: string[];
  quantityList?: number[]
  redirectToUpdateForm?: string;
  changePageToPagination: number;
  itemsPerPage?: number;
  

  showEditButton?: boolean;
  showDeleteButton?: boolean;
  showViewButton?: boolean;
  showAddButton?: boolean;

  handleDelete?: (index: any) => void;
  handleStatusUpdate?: (data: any) => void;
  handleView?: (data: any) => void;
  handleAdd?: (quantity: number, data: any) => void;
}

const ManagementTable: React.FC<IProps> = ({
  columnTitles,
  data,
  objectKeys,
  redirectToUpdateForm = "/",
  changePageToPagination,
  itemsPerPage = 5,
  quantityList = [0],
  showEditButton = false,
  showDeleteButton = false,
  showViewButton = false,
  showAddButton = false,
  handleDelete = () => {},
  handleStatusUpdate = () => {},
  handleView = () => {},
  handleAdd = () => {},
}) => {
  const navigate = useNavigate();

  const getNestedValue = (obj: any, path: string) => {
    return path.split(".").reduce((acc, key) => acc && acc[key], obj);
  };

  const updateNestedProperty = (obj: any, path: string[], value: any) => {
    if (path.length === 1) {
      obj[path[0]] = value;
    } else {
      updateNestedProperty(obj[path[0]], path.slice(1), value);
    }
  };

  const startIndex = (changePageToPagination - 1) * itemsPerPage;

  const paginatedData = data.slice(startIndex, startIndex + itemsPerPage);

  return (
    <Table responsive striped bordered hover>
      <thead>
        <tr>
          {columnTitles.map((column) => (
            <th key={column}>{column}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {paginatedData.map((data, index) => (
          <tr key={index}>
            <td>{startIndex + index + 1}</td>
            {objectKeys.map((key) => (
              <td key={key}>
                {typeof getNestedValue(data, key) === "boolean" ? (
                  <TableToggle
                    initialValue={getNestedValue(data, key)}
                    onToggle={() => {
                      const newData = { ...data };
                      updateNestedProperty(
                        newData,
                        key.split("."),
                        !getNestedValue(data, key)
                      );
                      handleStatusUpdate(newData);
                    }}
                  />
                ) : (
                  getNestedValue(data, key)
                )}
              </td>
            ))}
            <td>
              {showViewButton && (
                <StyledButton variant="success" onClick={handleView}>
                  <EyeIcon />
                </StyledButton>
              )}
              {showEditButton && (
                <StyledButton
                  variant="warning"
                  onClick={() => {
                    navigate(redirectToUpdateForm, { state: data });
                  }}
                >
                  <PencilIcon />
                </StyledButton>
              )}
              {showDeleteButton && (
                <StyledButton
                  variant="danger"
                  onClick={() => {
                    console.log(index);
                    handleDelete(index)
                  }}
                >
                  <ThrashIcon />
                </StyledButton>
              )}
              {showAddButton && (
                <CartQuantityUpdater
                  initialValue={quantityList[startIndex + index + 1]}
                  handleAdd={(quantity) => {handleAdd(quantity, data)}}
                />
              )}
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
};

export default ManagementTable;
