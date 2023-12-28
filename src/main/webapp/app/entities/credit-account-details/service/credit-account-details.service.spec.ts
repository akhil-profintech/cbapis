import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICreditAccountDetails } from '../credit-account-details.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../credit-account-details.test-samples';

import { CreditAccountDetailsService } from './credit-account-details.service';

const requireRestSample: ICreditAccountDetails = {
  ...sampleWithRequiredData,
};

describe('CreditAccountDetails Service', () => {
  let service: CreditAccountDetailsService;
  let httpMock: HttpTestingController;
  let expectedResult: ICreditAccountDetails | ICreditAccountDetails[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CreditAccountDetailsService);
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

    it('should create a CreditAccountDetails', () => {
      const creditAccountDetails = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(creditAccountDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a CreditAccountDetails', () => {
      const creditAccountDetails = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(creditAccountDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a CreditAccountDetails', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of CreditAccountDetails', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a CreditAccountDetails', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCreditAccountDetailsToCollectionIfMissing', () => {
      it('should add a CreditAccountDetails to an empty array', () => {
        const creditAccountDetails: ICreditAccountDetails = sampleWithRequiredData;
        expectedResult = service.addCreditAccountDetailsToCollectionIfMissing([], creditAccountDetails);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(creditAccountDetails);
      });

      it('should not add a CreditAccountDetails to an array that contains it', () => {
        const creditAccountDetails: ICreditAccountDetails = sampleWithRequiredData;
        const creditAccountDetailsCollection: ICreditAccountDetails[] = [
          {
            ...creditAccountDetails,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCreditAccountDetailsToCollectionIfMissing(creditAccountDetailsCollection, creditAccountDetails);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CreditAccountDetails to an array that doesn't contain it", () => {
        const creditAccountDetails: ICreditAccountDetails = sampleWithRequiredData;
        const creditAccountDetailsCollection: ICreditAccountDetails[] = [sampleWithPartialData];
        expectedResult = service.addCreditAccountDetailsToCollectionIfMissing(creditAccountDetailsCollection, creditAccountDetails);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(creditAccountDetails);
      });

      it('should add only unique CreditAccountDetails to an array', () => {
        const creditAccountDetailsArray: ICreditAccountDetails[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const creditAccountDetailsCollection: ICreditAccountDetails[] = [sampleWithRequiredData];
        expectedResult = service.addCreditAccountDetailsToCollectionIfMissing(creditAccountDetailsCollection, ...creditAccountDetailsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const creditAccountDetails: ICreditAccountDetails = sampleWithRequiredData;
        const creditAccountDetails2: ICreditAccountDetails = sampleWithPartialData;
        expectedResult = service.addCreditAccountDetailsToCollectionIfMissing([], creditAccountDetails, creditAccountDetails2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(creditAccountDetails);
        expect(expectedResult).toContain(creditAccountDetails2);
      });

      it('should accept null and undefined values', () => {
        const creditAccountDetails: ICreditAccountDetails = sampleWithRequiredData;
        expectedResult = service.addCreditAccountDetailsToCollectionIfMissing([], null, creditAccountDetails, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(creditAccountDetails);
      });

      it('should return initial array if no CreditAccountDetails is added', () => {
        const creditAccountDetailsCollection: ICreditAccountDetails[] = [sampleWithRequiredData];
        expectedResult = service.addCreditAccountDetailsToCollectionIfMissing(creditAccountDetailsCollection, undefined, null);
        expect(expectedResult).toEqual(creditAccountDetailsCollection);
      });
    });

    describe('compareCreditAccountDetails', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCreditAccountDetails(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCreditAccountDetails(entity1, entity2);
        const compareResult2 = service.compareCreditAccountDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCreditAccountDetails(entity1, entity2);
        const compareResult2 = service.compareCreditAccountDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCreditAccountDetails(entity1, entity2);
        const compareResult2 = service.compareCreditAccountDetails(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
