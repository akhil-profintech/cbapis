import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICreditBazaarIntegrator } from '../credit-bazaar-integrator.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../credit-bazaar-integrator.test-samples';

import { CreditBazaarIntegratorService } from './credit-bazaar-integrator.service';

const requireRestSample: ICreditBazaarIntegrator = {
  ...sampleWithRequiredData,
};

describe('CreditBazaarIntegrator Service', () => {
  let service: CreditBazaarIntegratorService;
  let httpMock: HttpTestingController;
  let expectedResult: ICreditBazaarIntegrator | ICreditBazaarIntegrator[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CreditBazaarIntegratorService);
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

    it('should create a CreditBazaarIntegrator', () => {
      const creditBazaarIntegrator = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(creditBazaarIntegrator).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a CreditBazaarIntegrator', () => {
      const creditBazaarIntegrator = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(creditBazaarIntegrator).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a CreditBazaarIntegrator', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of CreditBazaarIntegrator', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a CreditBazaarIntegrator', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCreditBazaarIntegratorToCollectionIfMissing', () => {
      it('should add a CreditBazaarIntegrator to an empty array', () => {
        const creditBazaarIntegrator: ICreditBazaarIntegrator = sampleWithRequiredData;
        expectedResult = service.addCreditBazaarIntegratorToCollectionIfMissing([], creditBazaarIntegrator);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(creditBazaarIntegrator);
      });

      it('should not add a CreditBazaarIntegrator to an array that contains it', () => {
        const creditBazaarIntegrator: ICreditBazaarIntegrator = sampleWithRequiredData;
        const creditBazaarIntegratorCollection: ICreditBazaarIntegrator[] = [
          {
            ...creditBazaarIntegrator,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCreditBazaarIntegratorToCollectionIfMissing(creditBazaarIntegratorCollection, creditBazaarIntegrator);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CreditBazaarIntegrator to an array that doesn't contain it", () => {
        const creditBazaarIntegrator: ICreditBazaarIntegrator = sampleWithRequiredData;
        const creditBazaarIntegratorCollection: ICreditBazaarIntegrator[] = [sampleWithPartialData];
        expectedResult = service.addCreditBazaarIntegratorToCollectionIfMissing(creditBazaarIntegratorCollection, creditBazaarIntegrator);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(creditBazaarIntegrator);
      });

      it('should add only unique CreditBazaarIntegrator to an array', () => {
        const creditBazaarIntegratorArray: ICreditBazaarIntegrator[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const creditBazaarIntegratorCollection: ICreditBazaarIntegrator[] = [sampleWithRequiredData];
        expectedResult = service.addCreditBazaarIntegratorToCollectionIfMissing(
          creditBazaarIntegratorCollection,
          ...creditBazaarIntegratorArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const creditBazaarIntegrator: ICreditBazaarIntegrator = sampleWithRequiredData;
        const creditBazaarIntegrator2: ICreditBazaarIntegrator = sampleWithPartialData;
        expectedResult = service.addCreditBazaarIntegratorToCollectionIfMissing([], creditBazaarIntegrator, creditBazaarIntegrator2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(creditBazaarIntegrator);
        expect(expectedResult).toContain(creditBazaarIntegrator2);
      });

      it('should accept null and undefined values', () => {
        const creditBazaarIntegrator: ICreditBazaarIntegrator = sampleWithRequiredData;
        expectedResult = service.addCreditBazaarIntegratorToCollectionIfMissing([], null, creditBazaarIntegrator, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(creditBazaarIntegrator);
      });

      it('should return initial array if no CreditBazaarIntegrator is added', () => {
        const creditBazaarIntegratorCollection: ICreditBazaarIntegrator[] = [sampleWithRequiredData];
        expectedResult = service.addCreditBazaarIntegratorToCollectionIfMissing(creditBazaarIntegratorCollection, undefined, null);
        expect(expectedResult).toEqual(creditBazaarIntegratorCollection);
      });
    });

    describe('compareCreditBazaarIntegrator', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCreditBazaarIntegrator(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCreditBazaarIntegrator(entity1, entity2);
        const compareResult2 = service.compareCreditBazaarIntegrator(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCreditBazaarIntegrator(entity1, entity2);
        const compareResult2 = service.compareCreditBazaarIntegrator(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCreditBazaarIntegrator(entity1, entity2);
        const compareResult2 = service.compareCreditBazaarIntegrator(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
