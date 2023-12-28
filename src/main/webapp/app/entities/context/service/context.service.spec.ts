import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IContext } from '../context.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../context.test-samples';

import { ContextService, RestContext } from './context.service';

const requireRestSample: RestContext = {
  ...sampleWithRequiredData,
  transactionDate: sampleWithRequiredData.transactionDate?.toJSON(),
};

describe('Context Service', () => {
  let service: ContextService;
  let httpMock: HttpTestingController;
  let expectedResult: IContext | IContext[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ContextService);
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

    it('should create a Context', () => {
      const context = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(context).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Context', () => {
      const context = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(context).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Context', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Context', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Context', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addContextToCollectionIfMissing', () => {
      it('should add a Context to an empty array', () => {
        const context: IContext = sampleWithRequiredData;
        expectedResult = service.addContextToCollectionIfMissing([], context);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(context);
      });

      it('should not add a Context to an array that contains it', () => {
        const context: IContext = sampleWithRequiredData;
        const contextCollection: IContext[] = [
          {
            ...context,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addContextToCollectionIfMissing(contextCollection, context);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Context to an array that doesn't contain it", () => {
        const context: IContext = sampleWithRequiredData;
        const contextCollection: IContext[] = [sampleWithPartialData];
        expectedResult = service.addContextToCollectionIfMissing(contextCollection, context);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(context);
      });

      it('should add only unique Context to an array', () => {
        const contextArray: IContext[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const contextCollection: IContext[] = [sampleWithRequiredData];
        expectedResult = service.addContextToCollectionIfMissing(contextCollection, ...contextArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const context: IContext = sampleWithRequiredData;
        const context2: IContext = sampleWithPartialData;
        expectedResult = service.addContextToCollectionIfMissing([], context, context2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(context);
        expect(expectedResult).toContain(context2);
      });

      it('should accept null and undefined values', () => {
        const context: IContext = sampleWithRequiredData;
        expectedResult = service.addContextToCollectionIfMissing([], null, context, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(context);
      });

      it('should return initial array if no Context is added', () => {
        const contextCollection: IContext[] = [sampleWithRequiredData];
        expectedResult = service.addContextToCollectionIfMissing(contextCollection, undefined, null);
        expect(expectedResult).toEqual(contextCollection);
      });
    });

    describe('compareContext', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareContext(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareContext(entity1, entity2);
        const compareResult2 = service.compareContext(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareContext(entity1, entity2);
        const compareResult2 = service.compareContext(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareContext(entity1, entity2);
        const compareResult2 = service.compareContext(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
