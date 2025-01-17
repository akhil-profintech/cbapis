import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { PlacedOfferDetailComponent } from './placed-offer-detail.component';

describe('PlacedOffer Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PlacedOfferDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: PlacedOfferDetailComponent,
              resolve: { placedOffer: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(PlacedOfferDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load placedOffer on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', PlacedOfferDetailComponent);

      // THEN
      expect(instance.placedOffer).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
