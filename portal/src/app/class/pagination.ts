export class Pagination {

    itemCount: number = 0;
    pageSize: number = 0;
    pageSizeOptions: number[] = [];
    currentPage: number = 0;
    sort: string = "";

    constructor(count: number, size: number, sizeOptions: number [], page: number, stringSort: string){
        this.itemCount = count;
        this.pageSize = size;
        this.pageSizeOptions = sizeOptions;
        this.currentPage = page;
        this.sort = stringSort;
    }
}
