import {Book} from "./book";

export interface GetResponse {
  _embedded: {
    bookDtoes: Book[];
    _links: {self: {href: string}};
  };
}
