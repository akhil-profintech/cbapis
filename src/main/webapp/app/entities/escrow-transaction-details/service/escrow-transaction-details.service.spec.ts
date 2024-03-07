import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEscrowTransactionDetails } from '../escrow-transaction-details.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../escrow-transaction-details.test-samples';

import { EscrowTransactionDetailsService } from './escrow-transaction-details.service';

const requireRestSample: IEscrowTransactionDetails = {
  ...sampleWithRequiredData,
};

describe('EscrowTransactionDetails Service', () => {
  let service: EscrowTransactionDetailsService;
  let httpMock: HttpTestingController;
  let expectedResult: IEscrowTransactionDetails | IEscrowTransactionDetails[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(EscrowTransactionDetailsService);
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

    it('should create a EscrowTransactionDetails', () => {
      const escrowTransactionDetails = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(escrowTransactionDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a EscrowTransactionDetails', () => {
      const escrowTransactionDetails = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(escrowTransactionDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a EscrowTransactionDetails', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of EscrowTransactionDetails', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a EscrowTransactionDetails', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addEscrowTransactionDetailsToCollectionIfMissing', () => {
      it('should add a EscrowTransactionDetails to an empty array', () => {
        const escrowTransactionDetails: IEscrowTransactionDetails = sampleWithRequiredData;
        expectedResult = service.addEscrowTransactionDetailsToCollectionIfMissing([], escrowTransactionDetails);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(escrowTransactionDetails);
      });

      it('should not add a EscrowTransactionDetails to an array that contains it', () => {
        const escrowTransactionDetails: IEscrowTransactionDetails = sampleWithRequiredData;
        const escrowTransactionDetailsCollection: IEscrowTransactionDetails[] = [
          {
            ...escrowTransactionDetails,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addEscrowTransactionDetailsToCollectionIfMissing(
          escrowTransactionDetailsCollection,
          escrowTransactionDetails,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a EscrowTransactionDetails to an array that doesn't contain it", () => {
        const escrowTransactionDetails: IEscrowTransactionDetails = sampleWithRequiredData;
        const escrowTransactionDetailsCollection: IEscrowTransactionDetails[] = [sampleWithPartialData];
        expectedResult = service.addEscrowTransactionDetailsToCollectionIfMissing(
          escrowTransactionDetailsCollection,
          escrowTransactionDetails,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(escrowTransactionDetails);
      });

      it('should add only unique EscrowTransactionDetails to an array', () => {
        const escrowTransactionDetailsArray: IEscrowTransactionDetails[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const escrowTransactionDetailsCollection: IEscrowTransactionDetails[] = [sampleWithRequiredData];
        expectedResult = service.addEscrowTransactionDetailsToCollectionIfMissing(
          escrowTransactionDetailsCollection,
          ...escrowTransactionDetailsArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const escrowTransactionDetails: IEscrowTransactionDetails = sampleWithRequiredData;
        const escrowTransactionDetails2: IEscrowTransactionDetails = sampleWithPartialData;
        expectedResult = service.addEscrowTransactionDetailsToCollectionIfMissing([], escrowTransactionDetails, escrowTransactionDetails2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(escrowTransactionDetails);
        expect(expectedResult).toContain(escrowTransactionDetails2);
      });

      it('should accept null and undefined values', () => {
        const escrowTransactionDetails: IEscrowTransactionDetails = sampleWithRequiredData;
        expectedResult = service.addEscrowTransactionDetailsToCollectionIfMissing([], null, escrowTransactionDetails, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(escrowTransactionDetails);
      });

      it('should return initial array if no EscrowTransactionDetails is added', () => {
        const escrowTransactionDetailsCollection: IEscrowTransactionDetails[] = [sampleWithRequiredData];
        expectedResult = service.addEscrowTransactionDetailsToCollectionIfMissing(escrowTransactionDetailsCollection, undefined, null);
        expect(expectedResult).toEqual(escrowTransactionDetailsCollection);
      });
    });

    describe('compareEscrowTransactionDetails', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareEscrowTransactionDetails(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareEscrowTransactionDetails(entity1, entity2);
        const compareResult2 = service.compareEscrowTransactionDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareEscrowTransactionDetails(entity1, entity2);
        const compareResult2 = service.compareEscrowTransactionDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareEscrowTransactionDetails(entity1, entity2);
        const compareResult2 = service.compareEscrowTransactionDetails(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
