import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICollectionTransactionDetails } from '../collection-transaction-details.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../collection-transaction-details.test-samples';

import { CollectionTransactionDetailsService } from './collection-transaction-details.service';

const requireRestSample: ICollectionTransactionDetails = {
  ...sampleWithRequiredData,
};

describe('CollectionTransactionDetails Service', () => {
  let service: CollectionTransactionDetailsService;
  let httpMock: HttpTestingController;
  let expectedResult: ICollectionTransactionDetails | ICollectionTransactionDetails[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CollectionTransactionDetailsService);
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

    it('should create a CollectionTransactionDetails', () => {
      const collectionTransactionDetails = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(collectionTransactionDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a CollectionTransactionDetails', () => {
      const collectionTransactionDetails = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(collectionTransactionDetails).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a CollectionTransactionDetails', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of CollectionTransactionDetails', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a CollectionTransactionDetails', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCollectionTransactionDetailsToCollectionIfMissing', () => {
      it('should add a CollectionTransactionDetails to an empty array', () => {
        const collectionTransactionDetails: ICollectionTransactionDetails = sampleWithRequiredData;
        expectedResult = service.addCollectionTransactionDetailsToCollectionIfMissing([], collectionTransactionDetails);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(collectionTransactionDetails);
      });

      it('should not add a CollectionTransactionDetails to an array that contains it', () => {
        const collectionTransactionDetails: ICollectionTransactionDetails = sampleWithRequiredData;
        const collectionTransactionDetailsCollection: ICollectionTransactionDetails[] = [
          {
            ...collectionTransactionDetails,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCollectionTransactionDetailsToCollectionIfMissing(
          collectionTransactionDetailsCollection,
          collectionTransactionDetails,
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CollectionTransactionDetails to an array that doesn't contain it", () => {
        const collectionTransactionDetails: ICollectionTransactionDetails = sampleWithRequiredData;
        const collectionTransactionDetailsCollection: ICollectionTransactionDetails[] = [sampleWithPartialData];
        expectedResult = service.addCollectionTransactionDetailsToCollectionIfMissing(
          collectionTransactionDetailsCollection,
          collectionTransactionDetails,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(collectionTransactionDetails);
      });

      it('should add only unique CollectionTransactionDetails to an array', () => {
        const collectionTransactionDetailsArray: ICollectionTransactionDetails[] = [
          sampleWithRequiredData,
          sampleWithPartialData,
          sampleWithFullData,
        ];
        const collectionTransactionDetailsCollection: ICollectionTransactionDetails[] = [sampleWithRequiredData];
        expectedResult = service.addCollectionTransactionDetailsToCollectionIfMissing(
          collectionTransactionDetailsCollection,
          ...collectionTransactionDetailsArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const collectionTransactionDetails: ICollectionTransactionDetails = sampleWithRequiredData;
        const collectionTransactionDetails2: ICollectionTransactionDetails = sampleWithPartialData;
        expectedResult = service.addCollectionTransactionDetailsToCollectionIfMissing(
          [],
          collectionTransactionDetails,
          collectionTransactionDetails2,
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(collectionTransactionDetails);
        expect(expectedResult).toContain(collectionTransactionDetails2);
      });

      it('should accept null and undefined values', () => {
        const collectionTransactionDetails: ICollectionTransactionDetails = sampleWithRequiredData;
        expectedResult = service.addCollectionTransactionDetailsToCollectionIfMissing([], null, collectionTransactionDetails, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(collectionTransactionDetails);
      });

      it('should return initial array if no CollectionTransactionDetails is added', () => {
        const collectionTransactionDetailsCollection: ICollectionTransactionDetails[] = [sampleWithRequiredData];
        expectedResult = service.addCollectionTransactionDetailsToCollectionIfMissing(
          collectionTransactionDetailsCollection,
          undefined,
          null,
        );
        expect(expectedResult).toEqual(collectionTransactionDetailsCollection);
      });
    });

    describe('compareCollectionTransactionDetails', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCollectionTransactionDetails(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCollectionTransactionDetails(entity1, entity2);
        const compareResult2 = service.compareCollectionTransactionDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCollectionTransactionDetails(entity1, entity2);
        const compareResult2 = service.compareCollectionTransactionDetails(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCollectionTransactionDetails(entity1, entity2);
        const compareResult2 = service.compareCollectionTransactionDetails(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
