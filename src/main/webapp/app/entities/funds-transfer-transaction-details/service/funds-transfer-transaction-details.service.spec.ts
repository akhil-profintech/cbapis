import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IFundsTransferTransactionDetails } from '../funds-transfer-transaction-details.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../funds-transfer-transaction-details.test-samples';

import { FundsTransferTransactionDetailsService } from './funds-transfer-transaction-details.service';

const requireRestSample: IFundsTransferTransactionDetails = {
  ...sampleWithRequiredData,
};

describe('FundsTransferTransactionDetails Service', () => {
  let service: FundsTransferTransactionDetailsService;
  let httpMock: HttpTestingController;
  let expectedResult: IFundsTransferTransactionDetails | IFundsTransferTransactionDetails[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FundsTransferTransactionDetailsService);
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

    it('should create a FundsTransferTransactionDetails', () => {
      const fundsTransferTransactionDetails = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(fundsTransferTransactionDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FundsTransferTransactionDetails', () => {
      const fundsTransferTransactionDetails = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(fundsTransferTransactionDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FundsTransferTransactionDetails', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FundsTransferTransactionDetails', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FundsTransferTransactionDetails', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFundsTransferTransactionDetailsToCollectionIfMissing', () => {
      it('should add a FundsTransferTransactionDetails to an empty array', () => {
        const fundsTransferTransactionDetails: IFundsTransferTransactionDetails = sampleWithRequiredData;
        expectedResult = service.addFundsTransferTransactionDetailsToCollectionIfMissing([], fundsTransferTransactionDetails);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(fundsTransferTransactionDetails);
      });

      it('should not add a FundsTransferTransactionDetails to an array that contains it', () => {
        const fundsTransferTransactionDetails: IFundsTransferTransactionDetails = sampleWithRequiredData;
        const fundsTransferTransactionDetailsCollection: IFundsTransferTransactionDetails[] = [
          {
            ...fundsTransferTransactionDetails,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFundsTransferTransactionDetailsToCollectionIfMissing(
          fundsTransferTransactionDetailsCollection,
          fundsTransferTransactionDetails,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FundsTransferTransactionDetails to an array that doesn't contain it", () => {
        const fundsTransferTransactionDetails: IFundsTransferTransactionDetails = sampleWithRequiredData;
        const fundsTransferTransactionDetailsCollection: IFundsTransferTransactionDetails[] = [sampleWithPartialData];
        expectedResult = service.addFundsTransferTransactionDetailsToCollectionIfMissing(
          fundsTransferTransactionDetailsCollection,
          fundsTransferTransactionDetails,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(fundsTransferTransactionDetails);
      });

      it('should add only unique FundsTransferTransactionDetails to an array', () => {
        const fundsTransferTransactionDetailsArray: IFundsTransferTransactionDetails[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const fundsTransferTransactionDetailsCollection: IFundsTransferTransactionDetails[] = [sampleWithRequiredData];
        expectedResult = service.addFundsTransferTransactionDetailsToCollectionIfMissing(
          fundsTransferTransactionDetailsCollection,
          ...fundsTransferTransactionDetailsArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const fundsTransferTransactionDetails: IFundsTransferTransactionDetails = sampleWithRequiredData;
        const fundsTransferTransactionDetails2: IFundsTransferTransactionDetails = sampleWithPartialData;
        expectedResult = service.addFundsTransferTransactionDetailsToCollectionIfMissing(
          [],
          fundsTransferTransactionDetails,
          fundsTransferTransactionDetails2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(fundsTransferTransactionDetails);
        expect(expectedResult).toContain(fundsTransferTransactionDetails2);
      });

      it('should accept null and undefined values', () => {
        const fundsTransferTransactionDetails: IFundsTransferTransactionDetails = sampleWithRequiredData;
        expectedResult = service.addFundsTransferTransactionDetailsToCollectionIfMissing(
          [],
          null,
          fundsTransferTransactionDetails,
          undefined,
        );
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(fundsTransferTransactionDetails);
      });

      it('should return initial array if no FundsTransferTransactionDetails is added', () => {
        const fundsTransferTransactionDetailsCollection: IFundsTransferTransactionDetails[] = [sampleWithRequiredData];
        expectedResult = service.addFundsTransferTransactionDetailsToCollectionIfMissing(
          fundsTransferTransactionDetailsCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(fundsTransferTransactionDetailsCollection);
      });
    });

    describe('compareFundsTransferTransactionDetails', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFundsTransferTransactionDetails(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareFundsTransferTransactionDetails(entity1, entity2);
        const compareResult2 = service.compareFundsTransferTransactionDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareFundsTransferTransactionDetails(entity1, entity2);
        const compareResult2 = service.compareFundsTransferTransactionDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareFundsTransferTransactionDetails(entity1, entity2);
        const compareResult2 = service.compareFundsTransferTransactionDetails(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
