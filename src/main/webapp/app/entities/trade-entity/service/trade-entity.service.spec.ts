import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITradeEntity } from '../trade-entity.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../trade-entity.test-samples';

import { TradeEntityService } from './trade-entity.service';

const requireRestSample: ITradeEntity = {
  ...sampleWithRequiredData,
};

describe('TradeEntity Service', () => {
  let service: TradeEntityService;
  let httpMock: HttpTestingController;
  let expectedResult: ITradeEntity | ITradeEntity[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TradeEntityService);
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

    it('should create a TradeEntity', () => {
      const tradeEntity = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(tradeEntity).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a TradeEntity', () => {
      const tradeEntity = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(tradeEntity).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a TradeEntity', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of TradeEntity', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a TradeEntity', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTradeEntityToCollectionIfMissing', () => {
      it('should add a TradeEntity to an empty array', () => {
        const tradeEntity: ITradeEntity = sampleWithRequiredData;
        expectedResult = service.addTradeEntityToCollectionIfMissing([], tradeEntity);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tradeEntity);
      });

      it('should not add a TradeEntity to an array that contains it', () => {
        const tradeEntity: ITradeEntity = sampleWithRequiredData;
        const tradeEntityCollection: ITradeEntity[] = [
          {
            ...tradeEntity,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTradeEntityToCollectionIfMissing(tradeEntityCollection, tradeEntity);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TradeEntity to an array that doesn't contain it", () => {
        const tradeEntity: ITradeEntity = sampleWithRequiredData;
        const tradeEntityCollection: ITradeEntity[] = [sampleWithPartialData];
        expectedResult = service.addTradeEntityToCollectionIfMissing(tradeEntityCollection, tradeEntity);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tradeEntity);
      });

      it('should add only unique TradeEntity to an array', () => {
        const tradeEntityArray: ITradeEntity[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const tradeEntityCollection: ITradeEntity[] = [sampleWithRequiredData];
        expectedResult = service.addTradeEntityToCollectionIfMissing(tradeEntityCollection, ...tradeEntityArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const tradeEntity: ITradeEntity = sampleWithRequiredData;
        const tradeEntity2: ITradeEntity = sampleWithPartialData;
        expectedResult = service.addTradeEntityToCollectionIfMissing([], tradeEntity, tradeEntity2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tradeEntity);
        expect(expectedResult).toContain(tradeEntity2);
      });

      it('should accept null and undefined values', () => {
        const tradeEntity: ITradeEntity = sampleWithRequiredData;
        expectedResult = service.addTradeEntityToCollectionIfMissing([], null, tradeEntity, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tradeEntity);
      });

      it('should return initial array if no TradeEntity is added', () => {
        const tradeEntityCollection: ITradeEntity[] = [sampleWithRequiredData];
        expectedResult = service.addTradeEntityToCollectionIfMissing(tradeEntityCollection, undefined, null);
        expect(expectedResult).toEqual(tradeEntityCollection);
      });
    });

    describe('compareTradeEntity', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTradeEntity(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareTradeEntity(entity1, entity2);
        const compareResult2 = service.compareTradeEntity(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareTradeEntity(entity1, entity2);
        const compareResult2 = service.compareTradeEntity(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareTradeEntity(entity1, entity2);
        const compareResult2 = service.compareTradeEntity(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
