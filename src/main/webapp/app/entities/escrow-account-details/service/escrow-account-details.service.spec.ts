import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEscrowAccountDetails } from '../escrow-account-details.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../escrow-account-details.test-samples';

import { EscrowAccountDetailsService } from './escrow-account-details.service';

const requireRestSample: IEscrowAccountDetails = {
  ...sampleWithRequiredData,
};

describe('EscrowAccountDetails Service', () => {
  let service: EscrowAccountDetailsService;
  let httpMock: HttpTestingController;
  let expectedResult: IEscrowAccountDetails | IEscrowAccountDetails[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(EscrowAccountDetailsService);
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

    it('should create a EscrowAccountDetails', () => {
      const escrowAccountDetails = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(escrowAccountDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a EscrowAccountDetails', () => {
      const escrowAccountDetails = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(escrowAccountDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a EscrowAccountDetails', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of EscrowAccountDetails', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a EscrowAccountDetails', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addEscrowAccountDetailsToCollectionIfMissing', () => {
      it('should add a EscrowAccountDetails to an empty array', () => {
        const escrowAccountDetails: IEscrowAccountDetails = sampleWithRequiredData;
        expectedResult = service.addEscrowAccountDetailsToCollectionIfMissing([], escrowAccountDetails);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(escrowAccountDetails);
      });

      it('should not add a EscrowAccountDetails to an array that contains it', () => {
        const escrowAccountDetails: IEscrowAccountDetails = sampleWithRequiredData;
        const escrowAccountDetailsCollection: IEscrowAccountDetails[] = [
          {
            ...escrowAccountDetails,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addEscrowAccountDetailsToCollectionIfMissing(escrowAccountDetailsCollection, escrowAccountDetails);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a EscrowAccountDetails to an array that doesn't contain it", () => {
        const escrowAccountDetails: IEscrowAccountDetails = sampleWithRequiredData;
        const escrowAccountDetailsCollection: IEscrowAccountDetails[] = [sampleWithPartialData];
        expectedResult = service.addEscrowAccountDetailsToCollectionIfMissing(escrowAccountDetailsCollection, escrowAccountDetails);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(escrowAccountDetails);
      });

      it('should add only unique EscrowAccountDetails to an array', () => {
        const escrowAccountDetailsArray: IEscrowAccountDetails[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const escrowAccountDetailsCollection: IEscrowAccountDetails[] = [sampleWithRequiredData];
        expectedResult = service.addEscrowAccountDetailsToCollectionIfMissing(escrowAccountDetailsCollection, ...escrowAccountDetailsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const escrowAccountDetails: IEscrowAccountDetails = sampleWithRequiredData;
        const escrowAccountDetails2: IEscrowAccountDetails = sampleWithPartialData;
        expectedResult = service.addEscrowAccountDetailsToCollectionIfMissing([], escrowAccountDetails, escrowAccountDetails2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(escrowAccountDetails);
        expect(expectedResult).toContain(escrowAccountDetails2);
      });

      it('should accept null and undefined values', () => {
        const escrowAccountDetails: IEscrowAccountDetails = sampleWithRequiredData;
        expectedResult = service.addEscrowAccountDetailsToCollectionIfMissing([], null, escrowAccountDetails, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(escrowAccountDetails);
      });

      it('should return initial array if no EscrowAccountDetails is added', () => {
        const escrowAccountDetailsCollection: IEscrowAccountDetails[] = [sampleWithRequiredData];
        expectedResult = service.addEscrowAccountDetailsToCollectionIfMissing(escrowAccountDetailsCollection, undefined, null);
        expect(expectedResult).toEqual(escrowAccountDetailsCollection);
      });
    });

    describe('compareEscrowAccountDetails', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareEscrowAccountDetails(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareEscrowAccountDetails(entity1, entity2);
        const compareResult2 = service.compareEscrowAccountDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareEscrowAccountDetails(entity1, entity2);
        const compareResult2 = service.compareEscrowAccountDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareEscrowAccountDetails(entity1, entity2);
        const compareResult2 = service.compareEscrowAccountDetails(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
