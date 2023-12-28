import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IUpdateVA } from '../update-va.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../update-va.test-samples';

import { UpdateVAService } from './update-va.service';

const requireRestSample: IUpdateVA = {
  ...sampleWithRequiredData,
};

describe('UpdateVA Service', () => {
  let service: UpdateVAService;
  let httpMock: HttpTestingController;
  let expectedResult: IUpdateVA | IUpdateVA[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(UpdateVAService);
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

    it('should create a UpdateVA', () => {
      const updateVA = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(updateVA).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a UpdateVA', () => {
      const updateVA = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(updateVA).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a UpdateVA', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of UpdateVA', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a UpdateVA', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addUpdateVAToCollectionIfMissing', () => {
      it('should add a UpdateVA to an empty array', () => {
        const updateVA: IUpdateVA = sampleWithRequiredData;
        expectedResult = service.addUpdateVAToCollectionIfMissing([], updateVA);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(updateVA);
      });

      it('should not add a UpdateVA to an array that contains it', () => {
        const updateVA: IUpdateVA = sampleWithRequiredData;
        const updateVACollection: IUpdateVA[] = [
          {
            ...updateVA,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addUpdateVAToCollectionIfMissing(updateVACollection, updateVA);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a UpdateVA to an array that doesn't contain it", () => {
        const updateVA: IUpdateVA = sampleWithRequiredData;
        const updateVACollection: IUpdateVA[] = [sampleWithPartialData];
        expectedResult = service.addUpdateVAToCollectionIfMissing(updateVACollection, updateVA);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(updateVA);
      });

      it('should add only unique UpdateVA to an array', () => {
        const updateVAArray: IUpdateVA[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const updateVACollection: IUpdateVA[] = [sampleWithRequiredData];
        expectedResult = service.addUpdateVAToCollectionIfMissing(updateVACollection, ...updateVAArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const updateVA: IUpdateVA = sampleWithRequiredData;
        const updateVA2: IUpdateVA = sampleWithPartialData;
        expectedResult = service.addUpdateVAToCollectionIfMissing([], updateVA, updateVA2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(updateVA);
        expect(expectedResult).toContain(updateVA2);
      });

      it('should accept null and undefined values', () => {
        const updateVA: IUpdateVA = sampleWithRequiredData;
        expectedResult = service.addUpdateVAToCollectionIfMissing([], null, updateVA, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(updateVA);
      });

      it('should return initial array if no UpdateVA is added', () => {
        const updateVACollection: IUpdateVA[] = [sampleWithRequiredData];
        expectedResult = service.addUpdateVAToCollectionIfMissing(updateVACollection, undefined, null);
        expect(expectedResult).toEqual(updateVACollection);
      });
    });

    describe('compareUpdateVA', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareUpdateVA(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareUpdateVA(entity1, entity2);
        const compareResult2 = service.compareUpdateVA(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareUpdateVA(entity1, entity2);
        const compareResult2 = service.compareUpdateVA(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareUpdateVA(entity1, entity2);
        const compareResult2 = service.compareUpdateVA(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
