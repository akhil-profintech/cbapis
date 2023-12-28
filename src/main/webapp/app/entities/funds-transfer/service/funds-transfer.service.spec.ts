import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IFundsTransfer } from '../funds-transfer.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../funds-transfer.test-samples';

import { FundsTransferService } from './funds-transfer.service';

const requireRestSample: IFundsTransfer = {
  ...sampleWithRequiredData,
};

describe('FundsTransfer Service', () => {
  let service: FundsTransferService;
  let httpMock: HttpTestingController;
  let expectedResult: IFundsTransfer | IFundsTransfer[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FundsTransferService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a FundsTransfer', () => {
      const fundsTransfer = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(fundsTransfer).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FundsTransfer', () => {
      const fundsTransfer = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(fundsTransfer).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FundsTransfer', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FundsTransfer', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FundsTransfer', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFundsTransferToCollectionIfMissing', () => {
      it('should add a FundsTransfer to an empty array', () => {
        const fundsTransfer: IFundsTransfer = sampleWithRequiredData;
        expectedResult = service.addFundsTransferToCollectionIfMissing([], fundsTransfer);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(fundsTransfer);
      });

      it('should not add a FundsTransfer to an array that contains it', () => {
        const fundsTransfer: IFundsTransfer = sampleWithRequiredData;
        const fundsTransferCollection: IFundsTransfer[] = [
          {
            ...fundsTransfer,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFundsTransferToCollectionIfMissing(fundsTransferCollection, fundsTransfer);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FundsTransfer to an array that doesn't contain it", () => {
        const fundsTransfer: IFundsTransfer = sampleWithRequiredData;
        const fundsTransferCollection: IFundsTransfer[] = [sampleWithPartialData];
        expectedResult = service.addFundsTransferToCollectionIfMissing(fundsTransferCollection, fundsTransfer);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(fundsTransfer);
      });

      it('should add only unique FundsTransfer to an array', () => {
        const fundsTransferArray: IFundsTransfer[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const fundsTransferCollection: IFundsTransfer[] = [sampleWithRequiredData];
        expectedResult = service.addFundsTransferToCollectionIfMissing(fundsTransferCollection, ...fundsTransferArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const fundsTransfer: IFundsTransfer = sampleWithRequiredData;
        const fundsTransfer2: IFundsTransfer = sampleWithPartialData;
        expectedResult = service.addFundsTransferToCollectionIfMissing([], fundsTransfer, fundsTransfer2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(fundsTransfer);
        expect(expectedResult).toContain(fundsTransfer2);
      });

      it('should accept null and undefined values', () => {
        const fundsTransfer: IFundsTransfer = sampleWithRequiredData;
        expectedResult = service.addFundsTransferToCollectionIfMissing([], null, fundsTransfer, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(fundsTransfer);
      });

      it('should return initial array if no FundsTransfer is added', () => {
        const fundsTransferCollection: IFundsTransfer[] = [sampleWithRequiredData];
        expectedResult = service.addFundsTransferToCollectionIfMissing(fundsTransferCollection, undefined, null);
        expect(expectedResult).toEqual(fundsTransferCollection);
      });
    });

    describe('compareFundsTransfer', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFundsTransfer(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareFundsTransfer(entity1, entity2);
        const compareResult2 = service.compareFundsTransfer(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareFundsTransfer(entity1, entity2);
        const compareResult2 = service.compareFundsTransfer(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareFundsTransfer(entity1, entity2);
        const compareResult2 = service.compareFundsTransfer(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
