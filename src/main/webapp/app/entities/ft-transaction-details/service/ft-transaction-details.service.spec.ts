import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IFTTransactionDetails } from '../ft-transaction-details.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../ft-transaction-details.test-samples';

import { FTTransactionDetailsService } from './ft-transaction-details.service';

const requireRestSample: IFTTransactionDetails = {
  ...sampleWithRequiredData,
};

describe('FTTransactionDetails Service', () => {
  let service: FTTransactionDetailsService;
  let httpMock: HttpTestingController;
  let expectedResult: IFTTransactionDetails | IFTTransactionDetails[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FTTransactionDetailsService);
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

    it('should create a FTTransactionDetails', () => {
      const fTTransactionDetails = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(fTTransactionDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FTTransactionDetails', () => {
      const fTTransactionDetails = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(fTTransactionDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FTTransactionDetails', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FTTransactionDetails', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FTTransactionDetails', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFTTransactionDetailsToCollectionIfMissing', () => {
      it('should add a FTTransactionDetails to an empty array', () => {
        const fTTransactionDetails: IFTTransactionDetails = sampleWithRequiredData;
        expectedResult = service.addFTTransactionDetailsToCollectionIfMissing([], fTTransactionDetails);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(fTTransactionDetails);
      });

      it('should not add a FTTransactionDetails to an array that contains it', () => {
        const fTTransactionDetails: IFTTransactionDetails = sampleWithRequiredData;
        const fTTransactionDetailsCollection: IFTTransactionDetails[] = [
          {
            ...fTTransactionDetails,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFTTransactionDetailsToCollectionIfMissing(fTTransactionDetailsCollection, fTTransactionDetails);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FTTransactionDetails to an array that doesn't contain it", () => {
        const fTTransactionDetails: IFTTransactionDetails = sampleWithRequiredData;
        const fTTransactionDetailsCollection: IFTTransactionDetails[] = [sampleWithPartialData];
        expectedResult = service.addFTTransactionDetailsToCollectionIfMissing(fTTransactionDetailsCollection, fTTransactionDetails);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(fTTransactionDetails);
      });

      it('should add only unique FTTransactionDetails to an array', () => {
        const fTTransactionDetailsArray: IFTTransactionDetails[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const fTTransactionDetailsCollection: IFTTransactionDetails[] = [sampleWithRequiredData];
        expectedResult = service.addFTTransactionDetailsToCollectionIfMissing(fTTransactionDetailsCollection, ...fTTransactionDetailsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const fTTransactionDetails: IFTTransactionDetails = sampleWithRequiredData;
        const fTTransactionDetails2: IFTTransactionDetails = sampleWithPartialData;
        expectedResult = service.addFTTransactionDetailsToCollectionIfMissing([], fTTransactionDetails, fTTransactionDetails2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(fTTransactionDetails);
        expect(expectedResult).toContain(fTTransactionDetails2);
      });

      it('should accept null and undefined values', () => {
        const fTTransactionDetails: IFTTransactionDetails = sampleWithRequiredData;
        expectedResult = service.addFTTransactionDetailsToCollectionIfMissing([], null, fTTransactionDetails, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(fTTransactionDetails);
      });

      it('should return initial array if no FTTransactionDetails is added', () => {
        const fTTransactionDetailsCollection: IFTTransactionDetails[] = [sampleWithRequiredData];
        expectedResult = service.addFTTransactionDetailsToCollectionIfMissing(fTTransactionDetailsCollection, undefined, null);
        expect(expectedResult).toEqual(fTTransactionDetailsCollection);
      });
    });

    describe('compareFTTransactionDetails', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFTTransactionDetails(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareFTTransactionDetails(entity1, entity2);
        const compareResult2 = service.compareFTTransactionDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareFTTransactionDetails(entity1, entity2);
        const compareResult2 = service.compareFTTransactionDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareFTTransactionDetails(entity1, entity2);
        const compareResult2 = service.compareFTTransactionDetails(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
