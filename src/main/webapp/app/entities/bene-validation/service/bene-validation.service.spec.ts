import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IBeneValidation } from '../bene-validation.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../bene-validation.test-samples';

import { BeneValidationService } from './bene-validation.service';

const requireRestSample: IBeneValidation = {
  ...sampleWithRequiredData,
};

describe('BeneValidation Service', () => {
  let service: BeneValidationService;
  let httpMock: HttpTestingController;
  let expectedResult: IBeneValidation | IBeneValidation[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BeneValidationService);
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

    it('should create a BeneValidation', () => {
      const beneValidation = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(beneValidation).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a BeneValidation', () => {
      const beneValidation = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(beneValidation).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a BeneValidation', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of BeneValidation', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a BeneValidation', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addBeneValidationToCollectionIfMissing', () => {
      it('should add a BeneValidation to an empty array', () => {
        const beneValidation: IBeneValidation = sampleWithRequiredData;
        expectedResult = service.addBeneValidationToCollectionIfMissing([], beneValidation);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(beneValidation);
      });

      it('should not add a BeneValidation to an array that contains it', () => {
        const beneValidation: IBeneValidation = sampleWithRequiredData;
        const beneValidationCollection: IBeneValidation[] = [
          {
            ...beneValidation,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addBeneValidationToCollectionIfMissing(beneValidationCollection, beneValidation);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a BeneValidation to an array that doesn't contain it", () => {
        const beneValidation: IBeneValidation = sampleWithRequiredData;
        const beneValidationCollection: IBeneValidation[] = [sampleWithPartialData];
        expectedResult = service.addBeneValidationToCollectionIfMissing(beneValidationCollection, beneValidation);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(beneValidation);
      });

      it('should add only unique BeneValidation to an array', () => {
        const beneValidationArray: IBeneValidation[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const beneValidationCollection: IBeneValidation[] = [sampleWithRequiredData];
        expectedResult = service.addBeneValidationToCollectionIfMissing(beneValidationCollection, ...beneValidationArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const beneValidation: IBeneValidation = sampleWithRequiredData;
        const beneValidation2: IBeneValidation = sampleWithPartialData;
        expectedResult = service.addBeneValidationToCollectionIfMissing([], beneValidation, beneValidation2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(beneValidation);
        expect(expectedResult).toContain(beneValidation2);
      });

      it('should accept null and undefined values', () => {
        const beneValidation: IBeneValidation = sampleWithRequiredData;
        expectedResult = service.addBeneValidationToCollectionIfMissing([], null, beneValidation, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(beneValidation);
      });

      it('should return initial array if no BeneValidation is added', () => {
        const beneValidationCollection: IBeneValidation[] = [sampleWithRequiredData];
        expectedResult = service.addBeneValidationToCollectionIfMissing(beneValidationCollection, undefined, null);
        expect(expectedResult).toEqual(beneValidationCollection);
      });
    });

    describe('compareBeneValidation', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareBeneValidation(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareBeneValidation(entity1, entity2);
        const compareResult2 = service.compareBeneValidation(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareBeneValidation(entity1, entity2);
        const compareResult2 = service.compareBeneValidation(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareBeneValidation(entity1, entity2);
        const compareResult2 = service.compareBeneValidation(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
