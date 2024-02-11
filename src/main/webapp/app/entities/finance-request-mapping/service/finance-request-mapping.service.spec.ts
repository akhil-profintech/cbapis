import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IFinanceRequestMapping } from '../finance-request-mapping.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../finance-request-mapping.test-samples';

import { FinanceRequestMappingService } from './finance-request-mapping.service';

const requireRestSample: IFinanceRequestMapping = {
  ...sampleWithRequiredData,
};

describe('FinanceRequestMapping Service', () => {
  let service: FinanceRequestMappingService;
  let httpMock: HttpTestingController;
  let expectedResult: IFinanceRequestMapping | IFinanceRequestMapping[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FinanceRequestMappingService);
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

    it('should create a FinanceRequestMapping', () => {
      const financeRequestMapping = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(financeRequestMapping).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FinanceRequestMapping', () => {
      const financeRequestMapping = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(financeRequestMapping).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FinanceRequestMapping', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FinanceRequestMapping', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FinanceRequestMapping', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFinanceRequestMappingToCollectionIfMissing', () => {
      it('should add a FinanceRequestMapping to an empty array', () => {
        const financeRequestMapping: IFinanceRequestMapping = sampleWithRequiredData;
        expectedResult = service.addFinanceRequestMappingToCollectionIfMissing([], financeRequestMapping);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(financeRequestMapping);
      });

      it('should not add a FinanceRequestMapping to an array that contains it', () => {
        const financeRequestMapping: IFinanceRequestMapping = sampleWithRequiredData;
        const financeRequestMappingCollection: IFinanceRequestMapping[] = [
          {
            ...financeRequestMapping,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFinanceRequestMappingToCollectionIfMissing(financeRequestMappingCollection, financeRequestMapping);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FinanceRequestMapping to an array that doesn't contain it", () => {
        const financeRequestMapping: IFinanceRequestMapping = sampleWithRequiredData;
        const financeRequestMappingCollection: IFinanceRequestMapping[] = [sampleWithPartialData];
        expectedResult = service.addFinanceRequestMappingToCollectionIfMissing(financeRequestMappingCollection, financeRequestMapping);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(financeRequestMapping);
      });

      it('should add only unique FinanceRequestMapping to an array', () => {
        const financeRequestMappingArray: IFinanceRequestMapping[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const financeRequestMappingCollection: IFinanceRequestMapping[] = [sampleWithRequiredData];
        expectedResult = service.addFinanceRequestMappingToCollectionIfMissing(
          financeRequestMappingCollection,
          ...financeRequestMappingArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const financeRequestMapping: IFinanceRequestMapping = sampleWithRequiredData;
        const financeRequestMapping2: IFinanceRequestMapping = sampleWithPartialData;
        expectedResult = service.addFinanceRequestMappingToCollectionIfMissing([], financeRequestMapping, financeRequestMapping2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(financeRequestMapping);
        expect(expectedResult).toContain(financeRequestMapping2);
      });

      it('should accept null and undefined values', () => {
        const financeRequestMapping: IFinanceRequestMapping = sampleWithRequiredData;
        expectedResult = service.addFinanceRequestMappingToCollectionIfMissing([], null, financeRequestMapping, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(financeRequestMapping);
      });

      it('should return initial array if no FinanceRequestMapping is added', () => {
        const financeRequestMappingCollection: IFinanceRequestMapping[] = [sampleWithRequiredData];
        expectedResult = service.addFinanceRequestMappingToCollectionIfMissing(financeRequestMappingCollection, undefined, null);
        expect(expectedResult).toEqual(financeRequestMappingCollection);
      });
    });

    describe('compareFinanceRequestMapping', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFinanceRequestMapping(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareFinanceRequestMapping(entity1, entity2);
        const compareResult2 = service.compareFinanceRequestMapping(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareFinanceRequestMapping(entity1, entity2);
        const compareResult2 = service.compareFinanceRequestMapping(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareFinanceRequestMapping(entity1, entity2);
        const compareResult2 = service.compareFinanceRequestMapping(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
