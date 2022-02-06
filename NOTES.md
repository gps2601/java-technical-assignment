# Notes

Please add here any notes, assumptions and design decisions that might help up understand your though process.

Current state:
- Basket has a list of items
- Basket has an inner class which calculates the price - may need to delegate this responsibility to make it easier to test
- Total calculator works by:
    - Adding all items by getting the item price - item is an interface
    - subtracting the result of the discount method which currently returns Zero
- Items have a product and items also can currently be sold as unit price or by weighted price

Desired state:
- Need to be able to apply different discounts to products
- Discounts could be applicable to a single product or to multiple - could extend into categories?

I think I will need to create a component that is capable to applying discounts to the whole basket of goods.
I will need a way to identify products so that the component can apply eligible discounts.
It's not obvious to me whether items should be responsible for knowing discounts or whether the discounting component should hold that responsibility.
I will also need a way to apply discounts to a type of products.
